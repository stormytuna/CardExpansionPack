package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import cardexpansionpack.CardExpansionPack;

public class FirewallPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("FirewallPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private int cardsExhaustedThisTurn = 0;

    public FirewallPower(AbstractCreature owner, int amount) {
        super(ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void atStartOfTurn() {
        cardsExhaustedThisTurn = 0;
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.type == CardType.STATUS && cardsExhaustedThisTurn < amount) {
            cardsExhaustedThisTurn++;
            flash();
            addToBot(new DrawCardAction(1));
            addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
        }
    }
}
