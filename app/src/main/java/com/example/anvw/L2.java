package com.example.anvw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class L2 extends View {


    public static Paint getWallpaint() {
        return wallpaint;
    }

    int r = 0;
    int g = 0;
    int b = 0;

    private  enum Direction{
        UP, DOWN ,LEFT, RIGHT
    }

    private L2.Cell[][] cells;
    private L2.Cell player, exit,exit2;
    private static final int COLS = 13, ROWS = 21;
    private static final float WALL_THICKNESS = 4;
    private float cellsize, hMargin, vMargin;
    private static Paint wallpaint,playerPaint, exitPaint,exitPaint2, blackholePaint;
    private Random random;
    private Canvas myCanvas;
    private int winOptionA = 0,winOptionB = 0;
//    public static void setColor(int color) {
//        wallpaint.setColor(color);
//        invalidate();
//    }

    public L2(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);

        wallpaint = new Paint();
        wallpaint.setColor(Color.RED);
        wallpaint.setStrokeWidth(WALL_THICKNESS);

        playerPaint = new Paint();
        playerPaint.setColor(Color.CYAN);

        exitPaint = new Paint();
        exitPaint.setColor(Color.BLUE);

        exitPaint2 = new Paint();
        exitPaint2.setColor(Color.RED);

        blackholePaint = new Paint();
        blackholePaint.setColor(Color.DKGRAY);

        random = new Random();

        createMaze();

    }
    private L2.Cell getNeighbour(L2.Cell cell){
        ArrayList<L2.Cell> neighbours = new ArrayList<>();

        //left neighbour
        if(cell.col > 0)
            if(!cells[cell.col-1][cell.row].visited)
                neighbours.add(cells[cell.col-1][cell.row]);

        //right neighbour
        if(cell.col < COLS-1)
            if(!cells[cell.col+1][cell.row].visited)
                neighbours.add(cells[cell.col+1][cell.row]);

        //top neighbour
        if(cell.row > 0)
            if(!cells[cell.col][cell.row-1].visited)
                neighbours.add(cells[cell.col][cell.row-1]);

        //bottom neighbour
        if(cell.row < ROWS-1)
            if(!cells[cell.col][cell.row+1].visited)
                neighbours.add(cells[cell.col][cell.row+1]);

        if(neighbours.size() > 0) {
            int index = random.nextInt(neighbours.size());
            return neighbours.get(index);
        }
        return null;
    }

    private void removeWall(L2.Cell current, L2.Cell next){
        if(current.col == next.col && current.row == next.row+1){
            current.topWall = false;
            next.bottomWall = false;
        }
        if(current.col == next.col && current.row == next.row-1){
            current.bottomWall = false;
            next.topWall = false;
        }
        if(current.col == next.col+1 && current.row == next.row){
            current.leftWall = false;
            next.rightWall = false;
        }
        if(current.col == next.col-1 && current.row == next.row){
            current.rightWall = false;
            next.leftWall = false;
        }
        if(current.col == next.col+1 && current.row == next.row-1){
            current.rightWall = false;
            next.leftWall = false;
        }
        if(current.col == next.col-1 && current.row == next.row){
            current.rightWall = false;
            next.leftWall = false;
        }

    }

    private void createMaze(){
        Stack<L2.Cell> stack = new Stack<>();
        L2.Cell current, next;

        cells = new L2.Cell[COLS][ROWS];

        for(int x=0; x<COLS; x++){
            for(int y=0; y<ROWS; y++){
                cells[x][y] = new L2.Cell(x, y);
            }
        }

        player = cells[3][5];
        exit = cells[7][15];
        exit2 = cells[10][15];




        current = cells[4][5];
        current.visited = true;



        do {
            next = getNeighbour(current);
            if (next != null) {
                removeWall(current, next);
                stack.push(current);
                current = next;
                current.visited = true;



            }
            else {
                current = stack.pop();

            }

        }while(!stack.isEmpty());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        myCanvas = canvas;
        canvas.drawColor(Color.BLACK);

        int width = getWidth();
        int height = getHeight();

        if(width/height < COLS/ROWS)
            cellsize = width/(COLS+1);
        else
            cellsize = height/(ROWS+1);

        hMargin = (width-COLS*cellsize)/2;
        vMargin = (height-ROWS*cellsize)/2;

        canvas.translate(hMargin, vMargin);

        for(int x=0; x<COLS; x++){
            for(int y=0; y<ROWS; y++){
                if(cells[x][y].topWall)
                    canvas.drawLine(
                            x*cellsize,
                            y*cellsize,
                            (x+1)*cellsize,
                            y*cellsize,
                            wallpaint);

                if(cells[x][y].leftWall)
                    canvas.drawLine(
                            x*cellsize,
                            y*cellsize,
                            x*cellsize,
                            (y+1)*cellsize,
                            wallpaint);

                if(cells[x][y].bottomWall)
                    canvas.drawLine(
                            x*cellsize,
                            (y+1)*cellsize,
                            (x+1)*cellsize,
                            (y+1)*cellsize,
                            wallpaint);

                if(cells[x][y].rightWall)
                    canvas.drawLine(
                            (x+1)*cellsize,
                            y*cellsize,
                            (x+1)*cellsize,
                            (y+1)*cellsize,
                            wallpaint);
            }
        }

        float margin = cellsize/10;

        canvas.drawRect(
                player.col*cellsize+margin+5,
                player.row*cellsize+margin+5,
                (player.col+1)*cellsize-margin-5,
                (player.row+1)*cellsize-margin-5,
                playerPaint);




        canvas.drawRect(
                exit.col*cellsize+margin,
                exit.row*cellsize+margin,
                (exit.col+1)*cellsize-margin,
                (exit.row+1)*cellsize-margin,
                exitPaint);

        canvas.drawRect(
                exit2.col*cellsize+margin,
                exit2.row*cellsize+margin,
                (exit2.col+1)*cellsize-margin,
                (exit2.row+1)*cellsize-margin,
                exitPaint2);

    }

    private void movePlayer(L2.Direction direction){
        switch (direction){
            case UP:
                if(!player.topWall)
                    player = cells[player.col][player.row-1];

                break;
            case DOWN:
                if(!player.bottomWall)
                    player = cells[player.col][player.row+1];

                break;
            case LEFT:
                if(!player.leftWall)
                    player = cells[player.col-1][player.row];

                break;
            case RIGHT:
                if(!player.rightWall)
                    player = cells[player.col+1][player.row];

                break;
        }

        checkExit();
        invalidate();
    }

    private void checkExit() {
        if(player == exit || player == exit2) {
            //createMaze();
//            Toast to1 = Toast.makeText(this.getContext(),"LEVEL COMPLETED",Toast.LENGTH_LONG);
//            to1.show();
//            Level1Activity.checkWin();
            Player.setIsLevelComplete();
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
            return true;

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            float x = event.getX();
            float y = event.getY();

            float playerCenterX = hMargin + (player.col+0.5f)*cellsize;
            float playerCenterY = vMargin + (player.row+0.5f)*cellsize;

            float dx = x - playerCenterX;
            float dy = y - playerCenterY;

            float absDx = Math.abs(dx);
            float absDy = Math.abs(dy);

            if(absDx > cellsize || absDy > cellsize){
                if(absDx > absDy){
                    //move in x-direction
                    if(dx > 0) {
                        movePlayer(L2.Direction.RIGHT);
                    }
                    else {
                        movePlayer(L2.Direction.LEFT);
                    }
                }
                else{
                    //move in y-direction
                    if(dy > 0) {
                        movePlayer(L2.Direction.DOWN);
                    }
                    else {
                        movePlayer(L2.Direction.UP);
                    }
                }

            }
            return true;
        }

        return super.onTouchEvent(event);
    }

    private class Cell{
        boolean
                topWall = true,
                leftWall = true,
                bottomWall = true,
                rightWall = true,
                visited = false;

        int col, row;

        public Cell(int col, int row) {
            this.col = col;
            this.row = row;
        }

        public boolean isTopWall() {
            return topWall;
        }

        public boolean isLeftWall() {
            return leftWall;
        }

        public boolean isBottomWall() {
            return bottomWall;
        }

        public boolean isRightWall() {
            return rightWall;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }
    }

    private class BlackHole{
        boolean
                topWall = true,
                leftWall = true,
                bottomWall = true,
                rightWall = true,
                visited = false;

        int col, row;

        public BlackHole(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }

    public Canvas getMyCanvas() {
        return myCanvas;
    }


}
