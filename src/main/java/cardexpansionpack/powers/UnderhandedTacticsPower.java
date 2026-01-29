package cardexpansionpack.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.SoulboundPatch.AbstractPlayer_isCursed;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import cardexpansionpack.CardExpansionPack;

public class UnderhandedTacticsPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("UnderhandedTacticsPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    private int numDebuffsAppliedThisTurn = 0;
    public UnderhandedTacticsPower(AbstractCreature owner, int amount) {
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
        numDebuffsAppliedThisTurn = 0;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (numDebuffsAppliedThisTurn < amount && power.type == PowerType.DEBUFF && source == AbstractDungeon.player && target != owner && !target.hasPower(ArtifactPower.POWER_ID)) {
            numDebuffsAppliedThisTurn++;
            flash();
            addToBot(new DrawCardAction(1));
        }
    }
}
