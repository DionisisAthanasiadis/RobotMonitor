package robot;

public class RobotMonitor {
    private int row;
    private int column;
    private boolean status = false; // False for OFF, True for ON

    public RobotMonitor() {
        this.row = 1;
        this.column = 1;
    }
    
    public void resetPosition() {
        this.row = 1;
        this.column = 1;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean getStatus() {
        return this.status;
    }

    public boolean toggleStatus() {
        if (row == 1) {
            return false; // Cannot toggle to ON if in the first row
        }
        this.status = !this.status;
        return true;
    }

    public boolean isAtDoor() {
        return (row == 6 && column == 6);
    }

    public boolean moveUp() {
        if (inRange(row - 1)) {
            row--;
            return true;
        } else {
            return false;
        }
    }

    public boolean moveDown() {
        if (inRange(row + 1)) {
            row++;
            return true;
        } else {
            return false;
        }
    }

    public boolean moveLeft() {
        if (inRange(column - 1)) {
            column--;
            return true;
        } else {
            return false;
        }
    }

    public boolean moveRight() {
        if (inRange(column + 1)) {
            column++;
            return true;
        } else {
            return false;
        }
    }

    public boolean exit() {
        return isAtDoor();
    }

    private boolean inRange(int s) {
        final int MIN = 1;
        final int MAX = 6;
        return (s >= MIN && s <= MAX);
    }
}


