package cardexpansionpack.powers;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import cardexpansionpack.CardExpansionPack;

public class TrickshotPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("TrickshotPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public TrickshotPower(AbstractCreature owner) {
        super(ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @SpirePatch2(clz = AbstractCard.class, method = "applyPowers")
    public static class MakeShivsTargetAll {
        @SpirePostfixPatch
        public static void patch(AbstractCard __instance) {
            if (AbstractDungeon.player.hasPower(ID) && __instance instanceof Shiv) {
                __instance.target = CardTarget.ALL_ENEMY;
            }
        }
    }

    @SpirePatch2(clz = Shiv.class, method = "use")
    public static class MakeShivsActuallyDoDamageToAll {
        @SpirePrefixPatch
        public static SpireReturn<Void> patch(Shiv __instance) {
            if (AbstractDungeon.player.hasPower(ID)) {
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, __instance.damage, __instance.damageTypeForTurn, AttackEffect.SLASH_HORIZONTAL));
                return SpireReturn.Return();
            }

            return SpireReturn.Continue();
        }
    }
}
