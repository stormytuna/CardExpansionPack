package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;

import cardexpansionpack.CardExpansionPack;

public class SuppressPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("SuppressPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private AbstractStance stance;

    public SuppressPower(AbstractCreature owner, AbstractCreature source, AbstractStance stance) {
        super(ID, TYPE, TURN_BASED, owner, source, -1, false);

        if (stance != null) {
            this.stance = stance;
        } else {
            this.stance = new NeutralStance();
        }

        updateDescription();
    }

    @Override
    public void updateDescription() {
        String stanceName = stance.name;
        if  (stanceName == null) {
            // Neutral stance sets its name to null
            stanceName = DESCRIPTIONS[1];
        }
        description = DESCRIPTIONS[0] + stanceName;
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot(new ChangeStanceAction(stance.ID));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }
}
