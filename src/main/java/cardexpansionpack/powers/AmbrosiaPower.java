package cardexpansionpack.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import cardexpansionpack.CardExpansionPack;

public class AmbrosiaPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("AmbrosiaPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AmbrosiaPower(AbstractCreature owner) {
        super(ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.canUpgrade()) {
            flash();
            card.upgrade();
            card.superFlash();
            card.applyPowers();
        }
    }
}
