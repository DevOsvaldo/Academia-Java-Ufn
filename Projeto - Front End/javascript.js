$(document).ready(function(){
$('.submit').click(function(){
    var nome = $("#name").val();
    var email = $("#email").val();


    if(nome == "" || email == ""){
      var mensagem = "Favor preencher os dados corretamente!"
      $("#name").css("border-color","red")
      $("#email").css("border-color","red")
    } else{
      $("#name").css("border-color","black")
      $("#email").css("border-color","black")
      var mensagem = "Formulário enviado com sucesso!!"
    }
    $("#campoMensagem").text(mensagem)
}) 
})