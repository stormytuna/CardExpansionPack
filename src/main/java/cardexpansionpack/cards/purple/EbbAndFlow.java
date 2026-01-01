package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.EbbAndFlowPower;
import cardexpansionpack.util.CardStats;

public class EbbAndFlow extends BaseCard {
    public static final String ID = makeID("EbbAndFlow");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.POWER,
        CardRarity.RARE,
        CardTarget.SELF,
        1
    );

    private static final int POWER_AMOUNT = 1;
    private static final int POWER_AMOUNT_UPGRADE = 1;

    public EbbAndFlow() {
        super(ID, INFO);
        setMagic(POWER_AMOUNT, POWER_AMOUNT_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new EbbAndFlow();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EbbAndFlowPower(p, magicNumber)));
    }
}

