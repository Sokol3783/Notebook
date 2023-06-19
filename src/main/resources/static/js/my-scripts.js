document.addEventListener("DOMContentLoaded", function() {
  document.getElementById("create_new").addEventListener("click", createNewNote);
});

function changePageSize() {
  $("#searchForm").submit();
}

function createNewNote() {
  let form = document.querySelector("form");
  form.action = document.getElementById("create_new").value;
  form.method = "POST";
  form.submit();
}