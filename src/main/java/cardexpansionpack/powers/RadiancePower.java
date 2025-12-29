package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;

import cardexpansionpack.CardExpansionPack;

public class RadiancePower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("RadiancePower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public RadiancePower(AbstractCreature owner) {
        super(ID, TYPE, TURN_BASED, owner, -1);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        int cardCost = card.costForTurn;
        if (cardCost <= 0) {
            return;
        }

        DamageInfo damageInfo = new DamageInfo(owner, cardCost, DamageType.THORNS);
        addToTop(new DamageRandomEnemyAction(damageInfo, AttackEffect.FIRE));
    }
}
