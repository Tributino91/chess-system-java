package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourceposition(position);
		return board.piece(position).possibleMoves();
		
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourceposition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePice(p, target);
		return capturedPiece;
	}
	
	private void validateSourceposition(Position position) {
		if (!board.thereIsApiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
			
		}
		
	}
	
	private  void validateTargetPosition(Position  source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("the chosen piece can' t move to target position");
		}
		
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
