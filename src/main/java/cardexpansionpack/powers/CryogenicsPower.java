package cardexpansionpack.powers;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.powers.AbstractPower;

import cardexpansionpack.CardExpansionPack;

public class CryogenicsPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("CryogenicsPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public CryogenicsPower(AbstractCreature owner, int amount) {
        super(ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @SpirePatch(clz = Frost.class, method = "onEvoke")
    public static class OnFrostOrbEvoked {
        @SpirePostfixPatch
        public static void patch(Frost __instance) {
            AbstractPower cryogenicsPower = AbstractDungeon.player.getPower(ID);
            if (cryogenicsPower == null) {
                return;
            } 

            cryogenicsPower.flash();
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(cryogenicsPower.amount));
        }
    }
}
