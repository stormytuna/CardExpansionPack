package cardexpansionpack.cards.colorless;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Ambrosia extends BaseCard {
    public static final String ID = makeID("Ambrosia");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        0
    );

    private static final int CARDS_TO_DRAW = 1;
    private static final int CARDS_TO_DRAW_UPGRADE = 1;

    public Ambrosia() {
        super(ID, INFO);
        setMagic(CARDS_TO_DRAW, CARDS_TO_DRAW_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Ambrosia();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Doing draw pile shuffling for the DrawCardAction so our postfix further down works properly
        if (magicNumber > p.drawPile.size()) {
            addToBot(new EmptyDeckShuffleAction());
        }
        addToBot(new DrawCardAction(magicNumber));
        UpgradeAmbrosiaDraws.upgrade = true;
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "draw", paramtypez = {int.class})
    public static class UpgradeAmbrosiaDraws {
        public static boolean upgrade = false;

        @SpireInsertPatch(rloc = 12, localvars = "c")
        public static void patch(AbstractCard c) {
            if (upgrade && c.canUpgrade()) {
                c.upgrade();
                c.superFlash();
                c.applyPowers();
            }
        }
    }

    // Setting to false once entire action is finished as we can draw multiple and we want to upgrade all, not just the first
    @SpirePatch2(clz = DrawCardAction.class, method = "update")
    public static class ResetAmbrosiaUpgradeBool {
        @SpirePostfixPatch
        public static void patch(DrawCardAction __instance) {
            if (__instance.isDone) {
                UpgradeAmbrosiaDraws.upgrade = false;
            }
        }
    }
}
