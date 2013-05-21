package board;

import java.util.LinkedList;

public class SudokuSolver {

	
	@SuppressWarnings("unused")
	private DLXNode[][] dLXMatrix;
	private DLXHeader root;
	private LinkedList<DLXNode> soln;
	
	public SudokuSolver() {
	
	}
	
	@SuppressWarnings("unused")
	private void seek(int k){
		if(root.right == root) {
			//print solution
			return;
		}
		DLXHeader col = choseColumn();
		cover(col);
		for (DLXNode row = col.down; row != col; row = row.down) {
			soln.addLast(row);
			for (DLXNode j = row.right; j != row; j = j.right) {
				cover(j);
			}
			seek(k + 1);
			
			for (DLXNode j = row.left; j != row; j = j.left) {
				uncover(j);
			}
			soln.removeLast();
		}
		uncover(col);
		return;
	}
	
	private void cover(DLXNode c) {
		c.right.left = c.left;
		c.left.right = c.right;
		for (DLXNode i = c.down; i != c ; i = i.down) {
			for (DLXNode j = i.right; j != i; j = j.right) {
				j.down.up = j.up;
				j.up.down = j.down;
				j.column.size--;
			}
		}
	}
	
	private void uncover(DLXNode c) {
		for (DLXNode i = c.up; i != c; i = i.up) {
			for (DLXNode j = i.left; j != i; j = j.left) {
				j.column.size++;
				j.down.up = j;
				j.up.down = j;
			}
		}
		c.right.left = c;
		c.left.right = c;
	}

	private DLXHeader choseColumn() {
		return root.right;
	}
}
