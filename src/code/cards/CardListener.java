package code.cards;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;

import code.game.Game;
import code.game.golf.Golf;
import code.game.littlespider.Homecell;
import code.game.littlespider.LittleSpider;

public class CardListener implements MouseListener {
	private int id;
	private Game game;
	private Card card;
	
	public CardListener(int id1, Game game1, Card card1) {
		id=id1;
		game=game1;
		card=card1;
	}

	/**
	 * Determines what it done when the card is clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Return if this card isn't the top of the tableau
		if (!this.card.isTop()) {
			return;
		}

		// Get all of the Tableaus
		Pile[] tableaus = this.game.getTableaus();
		
		// If a tableau is selected (not null) and the same
		// tableau is clicked again, deselect the tableau
		if (game.tableauSelected() != null && game.tableauSelected().equals(tableaus[card.getTableauNum()])) {
			this.card.deselect();
			return;
		}
		
		if(this.game.isHomecellSelected()) {
			Card card = this.game.homecellSelected().takeCard();
			boolean added = tableaus[this.card.getTableauNum()].addCard(card, false);
			if(added) {
				card.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
				tableaus[card.getTableauNum()].getAllCards().get(1).setUnder();
				card.setTableauNum(card.getTableauNum());
				game.setMoves(game.getMoves() + 1);
				this.game.setBlankErrorText();
			}else {
				this.game.homecellSelected().addCard(card, true);
				this.game.setErrorText();
			}
			((Homecell) this.game.homecellSelected()).deselect();
			
			card.deselect();
			this.game.refresh();
			return;
		}
		
		// If no tableau card is selected yet, select this one
		if (!this.game.isTableauSelected()) {
			this.game.setTableauSelected(tableaus[card.getTableauNum()]);
			card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			return;
		}

		// If this is a Golf game, and a tableau is already selected, do nothing
		if (game instanceof Golf) {
			this.game.setErrorText();
			return;
		} 
		
		// If this is a Little Spider game, and a tableau is already selected, try to add to 
		// This card's parent tableau
		if (game instanceof LittleSpider) {
			if(this.game.isTableauSelected()) {
				// Attempt to add the card to tableau
				Card card = this.game.tableauSelected().getCard();
				boolean added = tableaus[card.getTableauNum()].addCard(card, false);
				if (added) {
					this.game.tableauSelected().takeCard();
					card.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
					tableaus[card.getTableauNum()].getAllCards().get(1).setUnder();
					card.setTableauNum(card.getTableauNum());
					game.setMoves(game.getMoves() + 1);
					this.game.setBlankErrorText();
				}else {
					this.game.setErrorText();
					card.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
				}
				card.deselect();
				this.game.refresh();
				return;
			}
			
		}
	}

	/** This method does nothing. */
	@Override
	public void mousePressed(MouseEvent e) {}
	/** This method does nothing. */
	@Override
	public void mouseReleased(MouseEvent e) {}
	/** This method does nothing. */
	@Override
	public void mouseEntered(MouseEvent e) {}
	/** This method does nothing. */
	@Override
	public void mouseExited(MouseEvent e) {}
	
	
}
