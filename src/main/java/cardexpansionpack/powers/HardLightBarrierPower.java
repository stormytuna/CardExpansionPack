package cardexpansionpack.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.CardExpansionPack;

public class HardLightBarrierPower extends BasePower {
    public static final String ID = CardExpansionPack.makeID("HardLightBarrierPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public HardLightBarrierPower(AbstractCreature owner, int amount) {
        super(ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.costForTurn == 0 || (card.freeToPlayOnce && card.cost != -1)) {
            addToBot(new GainBlockAction(owner, owner, amount));
            flash();
        }
    }
}
