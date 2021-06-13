package com.example.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.example.tic_tac_toe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var board : Array<Array<Button>>
    var player = true
    var turnCount = 0
    var boardStatus = Array(3){IntArray(3)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        board = arrayOf(
            arrayOf(binding.button1,binding.button2,binding.button3),
            arrayOf(binding.button4,binding.button5,binding.button6),
            arrayOf(binding.button7,binding.button8,binding.button9)
        )

        initBoardStatus()

        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        binding.resetBtn.setOnClickListener{
            player = true
            turnCount = 0
            initBoardStatus()
        }
    }

    private fun initBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j]=-1
            }
        }
        for(i in 0..2){
            for(j in 0..2){
                board[i][j].isEnabled=true
                board[i][j].text = ""
            }
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.button1 ->{
                updateValue(row=0,col=0,player=player)
            }
            R.id.button2 ->{
                updateValue(row=0,col=1,player=player)

            }
            R.id.button3 ->{
                updateValue(row=0,col=2,player=player)

            }
            R.id.button4 ->{
                updateValue(row=1,col=0,player=player)

            }
            R.id.button5 ->{
                updateValue(row=1,col=1,player=player)

            }
            R.id.button6 ->{
                updateValue(row=1,col=2,player=player)

            }
            R.id.button7 ->{
                updateValue(row=2,col=0,player=player)

            }
            R.id.button8 ->{
                updateValue(row=2,col=1,player=player)

            }
            R.id.button9 ->{
                updateValue(row=2,col=2,player=player)

            }

        }
        turnCount++
        player = !player

        if(player){
            updateDisplay("Player X Turn")
        }else{
            updateDisplay("Player O Turn")
        }

        if(turnCount==9){
            updateDisplay("Game DRAW")
        }
        checkWin()
    }

    private fun checkWin() {
        //Horizontal Rows
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                    updateDisplay("Player X is the Winner!")
                    break
                }else if(boardStatus[i][0]==0){
                    updateDisplay("Player O is the Winner!")
                    break
                }
            }
        }

        //Vertical Rows
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[0][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updateDisplay("Player X is the Winner!")
                    break
                }else if(boardStatus[0][i]==0){
                    updateDisplay("Player O is the Winner!")
                    break
                }
            }
        }
        //First Diagonal
            if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2]){
                if(boardStatus[0][0]==1){
                    updateDisplay("Player X is the Winner!")
                }else if(boardStatus[0][0]==0){
                    updateDisplay("Player O is the Winner!")

                }
            }
        //Second Diagonal
        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[0][2]==boardStatus[2][0]){
            if(boardStatus[0][2]==1){
                updateDisplay("Player X is the Winner!")
            }else if(boardStatus[0][2]==0){
                updateDisplay("Player O is the Winner!")

            }
        }


    }

    private fun updateDisplay(s: String) {
            binding.textView.text = s
            if(s.contains("Winner")){
                disableBtn()
            }
    }

    private fun disableBtn(){
        for(i in board){
            for(button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0
        board[row][col].apply {
                isEnabled = false
                setText(text)
            }
        boardStatus[row][col] = value
    }
}