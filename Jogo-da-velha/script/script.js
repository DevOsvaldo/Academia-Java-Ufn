const selectBox = document.querySelector(".select-box"),
selectXBtn = selectBox.querySelector(".playerX"),
selectOBtn = selectBox.querySelector(".playerO"),
playBoard = document.querySelector(".play-board"),
allBox = document.querySelectorAll("section span"),
players = document.querySelector(".players"),
resultBox = document.querySelector(".result-box"),
wonText = resultBox.querySelector(".won-text"),
resetBtn = resultBox.querySelector("button"),
rpontsBtn = resultBox.querySelector("#resetar")

let playerXWins = localStorage.getItem('playerXWins')||0;
let playerOWins = localStorage.getItem('playerOWins')||0;


window.onload = ()=>{  
    for (let i = 0; i < allBox.length; i++) {
      allBox[i].setAttribute("onclick","clickedBox(this)");
      
    }

    selectXBtn.onclick = ()=>{
        selectBox.classList.add("hide");
        playBoard.classList.add("show");
    }
    selectOBtn.onclick = ()=>{
        selectBox.classList.add("hide");
        playBoard.classList.add("show");
        players.setAttribute("class","players active player")
    }
    updateScore();
}

let playerXIcon = "fas fa-times";
let playerOIcon = "fas fa-circle";
let playerSign = "X";
let runBot = true;


function clickedBox(element){
  if(players.classList.contains("player")){
      playerSign = "O";
      element.innerHTML = `<i class="${playerOIcon}"></i>`;
      players.classList.remove("active");
      element.setAttribute("id", playerSign)
  } else{
      playerSign = "X";
      element.innerHTML = `<i class="${playerXIcon}"></i>`;
      players.classList.add("active");
      element.setAttribute("id", playerSign)
  }
  selectWinner();
  element.style.pointerEvents = "none";
  let randomDelayTime = ((Math.random()*1000)+ 100).toFixed();
  setTimeout(()=>{
    bot(runBot);
  },randomDelayTime);
  
  
}

function bot(runBot){
        if(runBot){
        let array = [];
        playerSign = "O";
        for (let i = 0; i < allBox.length; i++) {
            if(allBox[i].childElementCount == 0){
                array.push(i);
            }
            
        }
        let randomBox = array[Math.floor(Math.random() * array.length)];
        if(array.length > 0){
            if(players.classList.contains("player")){
                playerSign = "X";
                allBox[randomBox].innerHTML = `<i class="${playerXIcon}"></i>`;
                players.classList.add("active");
                allBox[randomBox].setAttribute("id", playerSign);
            
            }else{
                allBox[randomBox].innerHTML = `<i class="${playerOIcon}"></i>`;
                players.classList.remove("active");
                allBox[randomBox].setAttribute("id", playerSign);
            } 
            selectWinner(); 
        }
        allBox[randomBox].style.pointerEvents = "none";
    }
}

function getClass(idname){
    return document.querySelector(".box"+idname).id;
}

function checkClass(val1,val2,val3, sign){
    if(getClass(val1) == sign && getClass(val2) == sign && getClass(val3) == sign){
        return true;
    }
}
function updateScore() {
    const playerXScoreElement = document.querySelector(".playerX-score");
    const playerOScoreElement = document.querySelector(".playerO-score");

    playerXScoreElement.textContent = "JOGADOR(X):"+playerXWins;
    playerOScoreElement.textContent = "JOGADOR(O):"+playerOWins;
}
function selectWinner(){
  if(checkClass(1,2,3,playerSign) || checkClass(4,5,6, playerSign)|| checkClass(7,8,9,playerSign) || checkClass(1,4,7,playerSign)
  || checkClass(2,5,8,playerSign)|| checkClass(3,6,9,playerSign)||checkClass(1,5,9,playerSign)|| checkClass(3,5,7,playerSign))
{
  runBot = false;
  bot(runBot);
  if (playerSign === "X") {
            playerXWins++;
            localStorage.setItem('playerXWins',playerXWins);
        } else if (playerSign === "O") {
            playerOWins++;
            localStorage.setItem('playerOWins', playerOWins);
        }
  updateScore();
  setTimeout(()=>{
    playBoard.classList.remove("show");
    resultBox.classList.add("show");
  },500);
  wonText.innerHTML = `Jogador <p>${playerSign}</p> Ganhou!`;
  
  } else{
    if(getClass(1)!="" && getClass(2) != "" && getClass(3)!="" && getClass(4)!=""&& getClass(5)!=""&&
    getClass(6)!="" && getClass(7)!=""&& getClass(8)!=""&& getClass(9)!=""){
      runBot = false;
  bot(runBot);
  setTimeout(()=>{
    playBoard.classList.remove("show");
    resultBox.classList.add("show");
  },700);
  wonText.textContent = "JOGO EMPATOU!";
    }
  }
}
resetBtn.onclick = ()=>{
  window.location.reload();
}
rpontsBtn.onclick = ()=>{
  localStorage.clear();
  alert("Pontuação zerada!! QUE OS JOGOS COMECEM!");
  updateScore();

}