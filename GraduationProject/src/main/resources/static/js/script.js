const filtersContainer = document.getElementById('activeFilters');

const searchTopic = document.getElementById('searchTopic');
const filterCourse = document.getElementById('filterCourse');
const filterType = document.getElementById('filterType');
const filterStatus = document.getElementById('filterStatus');

const resetButton = document.getElementById('resetFilters');
const clearSearchBtn = document.getElementById('clearSearch');

function updateResetButtonVisibility() {
  const hasFilters = filtersContainer.children.length > 0;
  resetButton.style.display = hasFilters ? 'inline' : 'none';
}

function addFilter(type, value) {
  const existing = document.querySelector(`.filter-item[data-type="${type}"]`);
  if (existing) existing.remove();
  if (!value) return updateResetButtonVisibility();

  const div = document.createElement('div');
  div.className = 'filter-item';
  div.dataset.type = type;
  div.innerHTML = `${value} <span class="remove-filter">×</span>`;
  filtersContainer.appendChild(div);
  updateResetButtonVisibility();
}

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

clearSearchBtn.addEventListener('click', () => {
  searchTopic.value = '';
  const item = document.querySelector('.filter-item[data-type="topic"]');
  if (item) item.remove();
  updateResetButtonVisibility();
});

filterCourse.addEventListener('change', () => {
  addFilter('course', filterCourse.value);
});

filterType.addEventListener('change', () => {
  addFilter('type', filterType.value);
});

filterStatus.addEventListener('change', () => {
  addFilter('status', filterStatus.value);
});

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

resetButton.addEventListener('click', () => {
  filtersContainer.innerHTML = '';
  searchTopic.value = '';
  filterCourse.value = '';
  filterType.value = '';
  filterStatus.value = '';
  updateResetButtonVisibility();
});
