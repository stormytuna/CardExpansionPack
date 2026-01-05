package cardexpansionpack.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.BaseMod;

public class AddFromDiscardWithExclusionsAction extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("BetterToHandAction")).TEXT;

    private final HashSet<String> exclusionIds;

    public AddFromDiscardWithExclusionsAction(int count, String exclusionId) {
        this(count, new HashSet<String>(Arrays.asList(new String[] {exclusionId})));
    }

    public AddFromDiscardWithExclusionsAction(int count, HashSet<String> exclusionIds) {
        this.exclusionIds = exclusionIds;
        amount = count;
        actionType = ActionType.CARD_MANIPULATION;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        AbstractPlayer player = AbstractDungeon.player;

        if (this.duration == startDuration) {
            if (player.discardPile.isEmpty()) {
                isDone = true;
                return;
            }

            CardGroup cards = getAvailableCards();
            if (cards.size() <= amount) {
                addToHand(cards.group);
            } else {
                AbstractDungeon.gridSelectScreen.open(cards, amount, true, TEXT[0]);
            }

            tickDuration();
            return;
        }

        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            addToHand(AbstractDungeon.gridSelectScreen.selectedCards);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

        tickDuration();
    }

    private CardGroup getAvailableCards() {
        CardGroup cards = new CardGroup(CardGroupType.UNSPECIFIED);

        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (exclusionIds.contains(c.cardID)) {
                continue;
            }

            cards.addToTop(c);
        }

        return cards;
    }

    private void addToHand(ArrayList<AbstractCard> cards) {
        AbstractPlayer player = AbstractDungeon.player;

        for (AbstractCard c : cards) {
            if (player.hand.size() >= BaseMod.MAX_HAND_SIZE) {
                player.createHandIsFullDialog();
                isDone = true;
                return;
            }

            player.hand.addToHand(c);
            player.discardPile.removeCard(c);
            c.lighten(false);
            c.unhover();
            c.applyPowers();
        }

        for (AbstractCard c : player.discardPile.group) {
            c.unhover();
            c.target_x = CardGroup.DISCARD_PILE_X;
            c.target_y = 0f;
        }

        AbstractDungeon.player.hand.refreshHandLayout();
    }
}
