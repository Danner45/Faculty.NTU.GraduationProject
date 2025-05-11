const filtersContainer = document.getElementById('activeFilters');
const filterYear = document.getElementById('filterYear');
const filterStatus = document.getElementById('filterStatus');
const searchName = document.getElementById('searchName');
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

filterYear.addEventListener('change', () => {
  addFilter('year', filterYear.value);
});

filterStatus.addEventListener('change', () => {
  addFilter('status', filterStatus.value);
});

searchName.addEventListener('input', () => {
  const trimmed = searchName.value.trim();
  if (trimmed) {
    addFilter('name', trimmed);
  } else {
    const item = document.querySelector('.filter-item[data-type="name"]');
    if (item) item.remove();
    updateResetButtonVisibility();
  }
});

clearSearchBtn.addEventListener('click', () => {
  searchName.value = '';
  const item = document.querySelector('.filter-item[data-type="name"]');
  if (item) item.remove();
  updateResetButtonVisibility();
});

filtersContainer.addEventListener('click', function (e) {
  if (e.target.classList.contains('remove-filter')) {
    const item = e.target.parentElement;
    const type = item.dataset.type;
    item.remove();

    if (type === 'year') filterYear.value = '';
    if (type === 'status') filterStatus.value = '';
    if (type === 'name') searchName.value = '';
    updateResetButtonVisibility();
  }
});

resetButton.addEventListener('click', () => {
  filtersContainer.innerHTML = '';
  filterYear.value = '';
  filterStatus.value = '';
  searchName.value = '';
  updateResetButtonVisibility();
});
