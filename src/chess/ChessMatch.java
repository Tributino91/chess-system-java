package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j);
			}
		}
		return mat;
	}
	
	private void placeNewPice(char column, int row, ChessPiece piece) {
		board.placePice(piece, new ChessPosition(column, row).toPosition());
	}
	
	private void initialSetup() {
		placeNewPice('c', 1, new Rook(board, Color.WHITE));
		placeNewPice('c', 2, new Rook(board, Color.WHITE));
		placeNewPice('d', 2, new Rook(board, Color.WHITE));
		placeNewPice('e', 1, new Rook(board, Color.WHITE));
		placeNewPice('e', 2, new Rook(board, Color.WHITE));
		placeNewPice('d', 1, new King(board, Color.WHITE));
		
		placeNewPice('c', 7, new Rook(board, Color.BLACK));
		placeNewPice('c', 8, new Rook(board, Color.BLACK));
		placeNewPice('d', 7, new Rook(board, Color.BLACK));
		placeNewPice('e', 7, new Rook(board, Color.BLACK));
		placeNewPice('e', 8, new Rook(board, Color.BLACK));
		placeNewPice('e', 8, new King(board, Color.BLACK));
		
		
	}
}
