package cardexpansionpack.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;

import cardexpansionpack.CardExpansionPack;

public class CallousedPower extends BasePower implements OnCreateCardInterface {
    public static final String ID = CardExpansionPack.makeID("CallousedPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public CallousedPower(AbstractCreature owner, int amount) {
        super(ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

	@Override
	public void onCreateCard(AbstractCard card) {
        if (card.type == CardType.STATUS) {
            flash();
            addToBot(new GainBlockAction(owner, amount));
        }
	}
}
