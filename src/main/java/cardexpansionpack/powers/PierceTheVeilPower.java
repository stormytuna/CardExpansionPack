package cardexpansionpack.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;

import cardexpansionpack.CardExpansionPack;

public class PierceTheVeilPower extends BasePower implements OnCreateCardInterface {
    public static final String ID = CardExpansionPack.makeID("PierceTheVeilPower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PierceTheVeilPower(AbstractCreature owner, int amount) {
        super(ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

	@Override
	public void onCreateCard(AbstractCard card) {
        flash();
        DamageInfo info = new DamageInfo(owner, amount, DamageType.THORNS);
        addToBot(new DamageRandomEnemyAction(info, AttackEffect.BLUNT_LIGHT));
	}
}
