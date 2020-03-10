// GET
const display = document.getElementById("p-tag");

const displayList = document.getElementById("ul-tag");

$.ajax({
  type: 'GET',
  url: 'http://localhost:8080/api/v1/customers',
  success: function(result) {
    // displays the response as a string
    display.innerHTML = JSON.stringify(result);

    // display each entry in a list
    result.forEach((entry) => {
      const newLine = document.createElement("li");
      newLine.innerHTML = JSON.stringify(entry);

      displayList.appendChild(newLine);
    })
  },
  error: function(result) {
    display.innerHTML = JSON.stringify(result);
  }
})


// POST

const postForm = document.getElementById("postForm");

const postBtn = document.getElementById("postBtn");

postBtn.addEventListener("click", (e) => {
  e.preventDefault();

  const name = document.getElementById("nameInput");
  const cpf = document.getElementById("cpfInput");

  const inputs = {
    name: name.value,
    cpf: cpf.value
  }

  $.ajax({
    type: 'POST',
    url: '/api/v1/customers',
    data: JSON.stringify(inputs),
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    success: function(result) {
      document.getElementById("postMsg").innerHTML = "ok!";
    },
    error: function(result) {
      document.getElementById("postMsg").innerHTML = "erro!";
    }
  })
})
