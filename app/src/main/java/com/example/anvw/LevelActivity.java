package com.example.anvw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class LevelActivity extends View {
    private  enum Direction{
        UP, DOWN ,LEFT, RIGHT
    }

    private Cell[][] cells;
    private Cell player, exit;
    private static final int COLS = 7, ROWS = 10;
    private static final float WALL_THICKNESS = 8;
    private float cellsize, hMargin, vMargin;
    private Paint wallpaint,playerPaint, exitPaint;
    private Random random;

    public LevelActivity(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);

        wallpaint = new Paint();
        wallpaint.setColor(Color.WHITE);
        wallpaint.setStrokeWidth(WALL_THICKNESS);

        playerPaint = new Paint();
        playerPaint.setColor(Color.RED);

        exitPaint = new Paint();
        exitPaint.setColor(Color.BLUE);

        random = new Random();

        createMaze();

    }
    private Cell getNeighbour(Cell cell){
        ArrayList<Cell> neighbours = new ArrayList<>();

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

    private void removeWall(Cell current, Cell next){
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
    }

    private void createMaze(){
        Stack<Cell> stack = new Stack<>();
        Cell current, next;

        cells = new Cell[COLS][ROWS];

        for(int x=0; x<COLS; x++){
            for(int y=0; y<ROWS; y++){
                cells[x][y] = new Cell(x, y);
            }
        }

        player = cells[0][0];
        exit = cells[COLS-1][ROWS-1];

        current = cells[0][0];
        current.visited = true;

        do {
            next = getNeighbour(current);
            if (next != null) {
                removeWall(current, next);
                stack.push(current);
                current = next;
                current.visited = true;
            }
            else
                current = stack.pop();
        }while(!stack.empty());
    }

    @Override
    protected void onDraw(Canvas canvas) {
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
                player.col*cellsize+margin,
                player.row*cellsize+margin,
                (player.col+1)*cellsize-margin,
                (player.row+1)*cellsize-margin,
                playerPaint);

        canvas.drawRect(
                exit.col*cellsize+margin,
                exit.row*cellsize+margin,
                (exit.col+1)*cellsize-margin,
                (exit.row+1)*cellsize-margin,
                exitPaint);
    }

    private void movePlayer(Direction direction){
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

    private void checkExit(){
        if(player == exit)
            createMaze();
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
                    if(dx > 0)
                        movePlayer(Direction.RIGHT);

                    else
                        movePlayer(Direction.LEFT);

                }
                else{
                    //move in y-direction
                    if(dy > 0)
                        movePlayer(Direction.DOWN);

                    else
                        movePlayer(Direction.UP);

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
    }
}
