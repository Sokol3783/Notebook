function changePageSize() {
  $("#searchForm").submit();
}

const main = document.getElementById('modal-note')
main.addEventListener('show.bs.modal', event => {

  const button = event.relatedTarget
  const command = button.getAttribute('data-bs-whatever')
  const modalTitle = main.querySelector('.modal-title')

  const submit = document.getElementById("submit-button")
  const note = document.getElementById("note-form")

  function getFirstNameField() {
    return document.getElementById(`noteFirstName`);
  }

  function getSecondNameField() {
    return document.getElementById(`noteSecondName`);
  }

  function getLastNameField() {
    return document.getElementById(`noteLastName`);
  }

  function getIdField() {
    return document.getElementById(`noteId`);
  }


  function setEmptyNote() {
    let field = getFirstNameField()
    field.value = ""
    let field2 = getSecondNameField()
    field2.value = ""
    let field3 = getLastNameField()
    field3.value = ""
    let field4 = getIdField()
    field4.value = ""
  }

  function fillNoteFields(note, row) {
    console.log("all right we are here")

    let field = getFirstNameField()
    field.value = row.cells[1].innerText
    let field2 = getSecondNameField()
    field2.value = row.cells[2].innerText
    let field3 = getLastNameField()
    field3.value = row.cells[3].innerText
    let field4 = getIdField()
    field4.value = row.cells[0].innerText

  }

  if (command === 'edit') {
    submit.classList.add('btn-warning')
    note.action = "/u/notebook/note/update";
    fillNoteFields(note, button.parentNode.parentNode.parentNode);
  } else if (command == 'delete') {
    submit.classList.add('btn-danger')
    note.action = "/u/notebook/note/delete";
    fillNoteFields(note, button.parentNode.parentNode.parentNode);
  } else {
    submit.classList.add('btn-success')
    note.action = "/u/notebook/note/create";
    setEmptyNote(note);
  }
  submit.textContent = command;
  modalTitle.textContent = command;
})

const pagination = document.getElementById('pagination')

pagination.addEventListener('click', event => {
      const element = event.target;
      const value = element.innerText;

      const submit = document.getElementById("newPage")
      submit.innerText = value
      submit.value = value
    }
)