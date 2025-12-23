package cardexpansionpack.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import cardexpansionpack.CardExpansionPack;

public class HeadsUpDisplayPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("HeadsUpDisplayPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HeadsUpDisplayPower(AbstractCreature owner) {
        super(ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
