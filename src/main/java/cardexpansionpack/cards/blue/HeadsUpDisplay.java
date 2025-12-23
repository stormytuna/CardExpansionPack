package cardexpansionpack.cards.blue;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.LockOnPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.HeadsUpDisplayPower;
import cardexpansionpack.util.CardStats;

public class HeadsUpDisplay extends BaseCard {
    public static final String ID = makeID("HeadsUpDisplay");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.POWER,
        CardRarity.RARE,
        CardTarget.SELF,
        2
    );

    private static final int LOCKON_AMOUNT = 3;
    private static final int LOCKON_UPGRADE = 2;

    private static final float MULTIPLIER = 1.75f;
    private static final String MULTIPLIER_STRING = "75";

    public HeadsUpDisplay() {
        super(ID, INFO);
        setMagic(LOCKON_AMOUNT, LOCKON_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new HeadsUpDisplay();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster monster : (AbstractDungeon.getCurrRoom().monsters.monsters)) {
            if (monster.isDeadOrEscaped()) {
                continue;
            }

            addToBot(new ApplyPowerAction(monster, p, new LockOnPower(monster, this.magicNumber)));
        }

        if (p.hasPower(HeadsUpDisplayPower.ID)) {
            return;
        }

        // Needs to be first in queue for updateDescription patch logic
        // updateDescription needs to be called on LockOnPower when we have HeadsUpDisplayPower active
        addToTop(new ApplyPowerAction(p, p, new HeadsUpDisplayPower(p)));
    }

    @SpirePatch(clz = AbstractOrb.class, method = "applyLockOn")
    public static class IncreaseLockOnDamage {
        @SpirePrefixPatch
        public static SpireReturn<Integer> patch(AbstractCreature target, int dmg) {
            if (AbstractDungeon.player.hasPower(HeadsUpDisplayPower.ID)) {
                return SpireReturn.Return((int)((float)dmg * 1.75f));
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = LockOnPower.class, method = "updateDescription")
    public static class IncreaseDamageDisplayedByLockOnPower {
        @SpirePostfixPatch
        public static void patch(LockOnPower __instance) {
            if (__instance.owner != null && AbstractDungeon.player.hasPower(HeadsUpDisplayPower.ID)) {
                if (__instance.amount == 1) {
                    __instance.description = LockOnPower.DESCRIPTIONS[0] + MULTIPLIER_STRING + LockOnPower.DESCRIPTIONS[1] + __instance.amount + LockOnPower.DESCRIPTIONS[2];
                } else {
                    __instance.description = LockOnPower.DESCRIPTIONS[0] + MULTIPLIER_STRING + LockOnPower.DESCRIPTIONS[1] + __instance.amount + LockOnPower.DESCRIPTIONS[3];
                }  
            }
        }
    }
}

