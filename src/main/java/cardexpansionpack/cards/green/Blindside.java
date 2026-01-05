package cardexpansionpack.cards.green;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Blindside extends BaseCard {
    public static final String ID = makeID("Blindside");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.ATTACK,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        0
    );

    private static final int DAMAGE = 4;
    private static final int DAMAGE_UPGRADE = 2;

    public Blindside() {
        super(ID, INFO);
        setDamage(DAMAGE, DAMAGE_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Blindside();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(m, damage);
        addToBot(new DamageAction(m, info, AttackEffect.SLASH_VERTICAL));
    }

    private static void fetchBlindsides() {
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof Blindside) {
                AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(c));
            }
        }
    }

    @SpirePatch2(clz = DiscardAction.class, method = "update")
    public static class OnDiscardCard {
        @SpirePostfixPatch
        public static void patch(DiscardAction __instance, boolean ___endTurn) {
            if (__instance.isDone && !___endTurn) {
                fetchBlindsides();
            }
        }
    }

    @SpirePatch2(clz = DiscardSpecificCardAction.class, method = "update")
    public static class OnDiscardCard2 {
        @SpirePostfixPatch
        public static void patch(DiscardSpecificCardAction __instance) {
            if (__instance.isDone) {
                fetchBlindsides();
            }
        }
    }
}
