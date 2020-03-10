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

  const name = document.getElementById("nameInputPost");
  const cpf = document.getElementById("cpfInputPost");

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


// PUT

const putForm = document.getElementById("putForm");

const putBtn = document.getElementById("putBtn");

putBtn.addEventListener("click", (e) => {
  e.preventDefault();

  const id = document.getElementById("idInputPut");
  const name = document.getElementById("nameInputPut");
  const cpf = document.getElementById("cpfInputPut");

  const inputs = {
    name: name.value,
    cpf: cpf.value
  }

  $.ajax({
    type: 'PUT',
    url: '/api/v1/customers/' + id.value,
    data: JSON.stringify(inputs),
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    success: function(result) {
      console.log(result)
      document.getElementById("putMsg").innerHTML = "ok!";
    },
    error: function(result) {
      console.log(result)
      document.getElementById("putMsg").innerHTML = "erro!";
    }
  })
})
