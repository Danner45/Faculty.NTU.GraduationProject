const filtersContainer = document.getElementById('activeFilters');

const searchTopic = document.getElementById('searchTopic');
const filterCourse = document.getElementById('filterCourse');
const filterType = document.getElementById('filterType');
const filterStatus = document.getElementById('filterStatus');

const resetButton = document.getElementById('resetFilters');
const clearSearchBtn = document.getElementById('clearSearch');

// Ánh xạ trạng thái từ số sang chữ
const statusMap = {
  "0": "Đang chờ duyệt",
  "1": "Chờ đăng kí",
  "2": "Đang thực hiện",
  "3": "Đã hoàn thành"
};

function updateResetButtonVisibility() {
  const hasFilters = filtersContainer.children.length > 0;
  resetButton.style.display = hasFilters ? 'inline' : 'none';
}

function addFilter(type, label) {
  const existing = document.querySelector(`.filter-item[data-type="${type}"]`);
  if (existing) existing.remove();
  if (!label) return updateResetButtonVisibility();

  const div = document.createElement('div');
  div.className = 'filter-item';
  div.dataset.type = type;
  div.innerHTML = `${label} <span class="remove-filter">×</span>`;
  filtersContainer.appendChild(div);
  updateResetButtonVisibility();
}

// Tìm kiếm theo tên đề tài
searchTopic.addEventListener('input', () => {
  const trimmed = searchTopic.value.trim();
  if (trimmed) {
    addFilter('topic', trimmed);
  } else {
    const item = document.querySelector('.filter-item[data-type="topic"]');
    if (item) item.remove();
    updateResetButtonVisibility();
  }
});

// Xóa tìm kiếm
clearSearchBtn.addEventListener('click', () => {
  searchTopic.value = '';
  const item = document.querySelector('.filter-item[data-type="topic"]');
  if (item) item.remove();
  updateResetButtonVisibility();
});

// Lọc theo khóa
filterCourse.addEventListener('change', () => {
  const selectedOption = filterCourse.options[filterCourse.selectedIndex];
  const label = selectedOption.text;
  addFilter('course', label);
});

// Lọc theo loại đề tài
filterType.addEventListener('change', () => {
  const selectedOption = filterType.options[filterType.selectedIndex];
  const label = selectedOption.text;
  addFilter('type', label);
});

// Lọc theo tình trạng (dùng map)
filterStatus.addEventListener('change', () => {
  const selectedValue = filterStatus.value;
  const label = statusMap[selectedValue] || '';
  addFilter('status', label);
});

// Xóa từng filter
filtersContainer.addEventListener('click', function (e) {
  if (e.target.classList.contains('remove-filter')) {
    const item = e.target.parentElement;
    const type = item.dataset.type;
    item.remove();

    if (type === 'topic') searchTopic.value = '';
    if (type === 'course') filterCourse.value = '';
    if (type === 'type') filterType.value = '';
    if (type === 'status') filterStatus.value = '';

    updateResetButtonVisibility();
  }
});

// Reset tất cả filter
resetButton.addEventListener('click', () => {
  filtersContainer.innerHTML = '';
  searchTopic.value = '';
  filterCourse.value = '';
  filterType.value = '';
  filterStatus.value = '';
  updateResetButtonVisibility();
});
