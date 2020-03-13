function lala(maxValue) {
    for (var i = 1; i <= maxValue; i++) {
        if ((i % 2) == 0) {
            document.writeln("**</br>")
        } else {
            document.writeln("*</br>")
        }
    }
}

var contaBancaria = {
    numero: 1234,
    saldo: 0,
    deposita: function(valor) {
        if (valor > 0) {
            this.saldo += valor;
        } else {
            alert('Valor inválido!');
        }
    }
}

//Os objetos são acessados através de referências
var cliente = {
    nome: 'Rafael',
    conta: contaBancaria
}

var cliente2 = {
    nome: "André",
    conta: contaBancaria
}

console.log(cliente.conta.saldo + "<br>");
console.log(cliente2.conta.saldo + "<br>");
cliente.conta.deposita(100);
console.log(cliente.conta.saldo + "<br>");
console.log(cliente2.conta.saldo + "<br>");
cliente2.conta.deposita(200);
console.log(cliente.conta.saldo + "<br>");
console.log(cliente2.conta.saldo + "<br>");

// // PORTOTYPE
var curso = { sigla: "K11", nome: " Orientação a Objetos em Java " };
// criando um objeto sem propriedades
var novo_curso = {};
// definindo o primeiro objeto como protótipo do segundo
novo_curso.__proto__ = curso;
// imprime K11
console.log(novo_curso.sigla);
// imprime Orientação a Objetos em Java
console.log(novo_curso.nome);

//Criando novo objeto com método Object.create
// // criando um objeto com duas propriedades
// var curso = { sigla: "K11", nome: " Orientação a Objetos em Java " };
// // criando um objeto sem propriedades
// var novo_curso = {};
// // definindo o primeiro objeto como protótipo do segundo
// novo_curso = Object.create(curso);
// // imprime K11
// console.log(novo_curso.sigla);
// // imprime Orientação a Objetos em Java
// console.log(novo_curso.nome);

//Se uma propriedade for adicionada a um objeto, ela também será adicionada a todos os objetos
//que o utilizam como protótipo.
// var curso = { sigla: "K11", nome: " Orientação a Objetos em Java " };
// var novo_curso = Object.create(curso);
// curso.carga_horaria = 36;
// // imprime K11
// console.log(novo_curso.sigla);
// // imprime Orientação a Objetos em Java
// console.log(novo_curso.nome);
// // imprime 36
// console.log(novo_curso.carga_horaria);

//Por outro lado, se uma propriedade for adicionada a um objeto, ela não será adicionada no protótipo desse objeto.
// var curso = { sigla: "K11", nome: " Orientação a Objetos em Java " };
// var novo_curso = Object.create(curso);
// novo_curso.carga_horaria = 36;
// // imprime K11
// console.log(curso.sigla);
// // imprime Orientação a Objetos em Java
// console.log(curso.nome);
// // imprime undefined
// console.log(curso.carga_horaria);

// Se o valor de uma propriedade de um objeto for modificado, os objetos que o utilizam como protótipo podem ser afetados.
// var curso = { sigla: "K11", nome: " Orientação a Objetos em Java " };
// var novo_curso = Object.create(curso);
// curso.sigla = "K12";
// curso.nome = " Desenvolvimento Web com JSF2 e JPA2 ";
// // imprime K12
// console.log(novo_curso.sigla);
// // imprime Desenvolvimento Web com JSF2 e JPA2
// console.log(novo_curso.nome);

// Por outro lado, alterações nos valores das propriedades de um objeto não afetam o protótipo desse objeto
// var curso = { sigla: "K11", nome: " Orientação a Objetos em Java " };

// var novo_curso = Object.create(curso);

// novo_curso.sigla = "K12";
// novo_curso.nome = " Desenvolvimento Web com JSF2 e JPA2 ";

// // imprime K11
// console.log(curso.sigla);

// console.log(curso.nome);

// Considere um objeto que foi construído a partir de um protótipo. Se o valor de uma propriedade
// herdada do protótipo for alterada nesse objeto, ela se torna independente da propriedade no protótipo. Dessa forma, alterações no valor dessa propriedade no protótipo não afetam mais o valor dela
// no objeto gerado a partir do protótipo.
// var curso = { sigla: "K11", nome: " Orientação a Objetos em Java " };
// var novo_curso = Object.create(curso);
// novo_curso.sigla = "K12";
// novo_curso.nome = " Desenvolvimento Web com JSF2 e JPA2 ";
// curso.sigla = "K21";
// curso.nome = " Persistência com JPA2 e Hibernate ";

// // imprime K12
// console.log(novo_curso.sigla);

// // imprime Desenvolvimento Web com JSF2 e JPA2
// console.log(novo_curso.nome);

// Podemos remover uma propriedade de um objeto com a função delete
// var curso = { sigla: "K11", nome: " Orientação a Objetos em Java " };

// // imprime K11
// console.log(curso.sigla);

// delete curso.sigla;

// // imprime undefined
// console.log(curso.sigla);

// Podemos verificar se uma propriedade existe, podemos utilizar a função in.
// var curso = { sigla: "K11", nome: " Orientação a Objetos em Java " };
// // imprime true
// console.log("sigla" in curso);
// // imprime false
// console.log("carga_horaria" in curso);