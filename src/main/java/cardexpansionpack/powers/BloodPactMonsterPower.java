package cardexpansionpack.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import cardexpansionpack.CardExpansionPack;

public class BloodPactMonsterPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("BloodPactMonsterPower");

    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public BloodPactMonsterPower(AbstractCreature owner) {
        super(ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
