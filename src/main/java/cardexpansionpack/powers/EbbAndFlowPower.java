package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import cardexpansionpack.CardExpansionPack;

public class EbbAndFlowPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("EbbAndFlowPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private int cardsDrawnThisTurn = 0;

    public EbbAndFlowPower(AbstractCreature owner, int amount) {
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
        cardsDrawnThisTurn = 0;
    }

    @Override
    public void onScry() {
        if (cardsDrawnThisTurn < amount) {
            cardsDrawnThisTurn++;
            flash();
            addToBot(new DrawCardAction(1));
        }
    }
}
