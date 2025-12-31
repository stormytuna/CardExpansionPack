package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import cardexpansionpack.CardExpansionPack;

public class ForeshadowPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("ForeshadowPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ForeshadowPower(AbstractCreature owner) {
        super(ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        int retainedCards = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.retain || c.selfRetain) {
                retainedCards++;
            }
        }

        if (retainedCards <= 0) {
            return;
        }

        flash();
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, retainedCards, DamageType.HP_LOSS, AttackEffect.FIRE));
    }
}
