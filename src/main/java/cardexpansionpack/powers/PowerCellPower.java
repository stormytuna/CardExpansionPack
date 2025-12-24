package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;

import cardexpansionpack.CardExpansionPack;

public class PowerCellPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("PowerCellPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PowerCellPower(AbstractCreature owner, int amount) {
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
    public void onCardDraw(AbstractCard card) {
        if (card.type == CardType.POWER) {
            flash();
            addToBot(new GainEnergyAction(amount));
        }
    }
}
