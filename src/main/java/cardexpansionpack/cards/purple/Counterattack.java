package cardexpansionpack.cards.purple;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import cardexpansionpack.CardExpansionPack;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Counterattack extends BaseCard {
    public static final String ID = makeID("Counterattack");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.ATTACK,
        CardRarity.COMMON,
        CardTarget.ENEMY,
        3
    );

    private static final int DAMAGE = 8;
    private static final int DAMAGE_UPGRADE = 2;
    private static final int BLOCK = 8;
    private static final int BLOCK_UPGRADE = 2;

    public Counterattack() {
        super(ID, INFO);
        setDamage(DAMAGE, DAMAGE_UPGRADE);
        setBlock(BLOCK, BLOCK_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Counterattack();
    }

    @Override
    public void update() {
        super.update();

        if (ScryTracker.numScriesThisTurn > 0) {
            setCostForTurn(cost - ScryTracker.numScriesThisTurn);
        } else {
            costForTurn = cost;
            isCostModifiedForTurn = false;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.BLUNT_LIGHT));
        addToBot(new GainBlockAction(p, block));
    }

    @SpirePatch2(clz = ScryAction.class, method = "update")
    public static class ScryTracker {
        public static int numScriesThisTurn = 0;

        @SpirePostfixPatch
        public static void patch(ScryAction __instance) {
            if (__instance.isDone) {
                numScriesThisTurn++;
            }
        }
    }

    @SpirePatch2(clz = GameActionManager.class, method = "callEndOfTurnActions")
    public static class ResetScryTrackerAtTurnFinish {
        @SpirePostfixPatch
        public static void patch() {
            ScryTracker.numScriesThisTurn = 0;
        }
    }

    @SpirePatch2(clz = AbstractRoom.class, method = "endBattle")
    public static class ResetScryTrackerAtBattleFinish {
        @SpirePostfixPatch
        public static void patch() {
            ScryTracker.numScriesThisTurn = 0;
        }
    }
}

