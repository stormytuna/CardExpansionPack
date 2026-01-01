package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;

import cardexpansionpack.CardExpansionPack;

public class ShieldOfFaithPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("ShieldOfFaithPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ShieldOfFaithPower(AbstractCreature owner, int amount) {
        super(ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof MantraPower) {
            flash();
            addToBot(new GainBlockAction(owner, amount));
        }
    }
}
