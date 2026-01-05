package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.DexterityPower;

import cardexpansionpack.CardExpansionPack;

public class UncannyDodgePower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("UncannyDodgePower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public UncannyDodgePower(AbstractCreature owner, int amount) {
        super(ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, amount)));
    }
}
