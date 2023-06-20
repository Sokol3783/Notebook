function changePageSize() {
  $("#searchForm").submit();
}

const main = document.getElementById('modal-note')
main.addEventListener('show.bs.modal', event => {

  const button = event.relatedTarget
  const command = button.getAttribute('data-bs-whatever')
  const modalTitle = main.querySelector('.modal-title')

  const submit = document.getElementById("submit-button")
  const note = document.getElementById("note-form");

  function setEmptyNote(note) {
    note.getElementById('noteFirstName').value = "";
    note.getElementById('noteSecondName').value = "";
    note.getElementById('noteLastName').value = "";
    note.getElementById('noteId').value = "";
  }

  function fillNoteFields(note, button) {
    console.log("all right we are here")


  }

  if (command === 'edit') {
    submit.classList.add('btn-warning')
    note.action = "/u/notebook/note/update";
    fillNoteFields(note);
  } else if (command == 'delete') {
    submit.classList.add('btn-danger')
    note.action = "/u/notebook/note/delete";
    fillNoteFields(note);
  } else {
    submit.classList.add('btn-success')
    note.action = "/u/notebook/note/";
    setEmptyNote(note);
  }
  submit.textContent = command;
  modalTitle.textContent = command;
})