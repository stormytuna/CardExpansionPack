package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.CallousedPower;
import cardexpansionpack.util.CardStats;

public class Calloused extends BaseCard {
    public static final String ID = makeID("Calloused");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    private static final int AMOUNT = 4;
    private static final int AMOUNT_UPGRADE = 1;

    public Calloused() {
        super(ID, INFO);
        setMagic(AMOUNT, AMOUNT_UPGRADE);
        setInnate(false, true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Calloused();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CallousedPower(p, magicNumber), magicNumber));
    }
}

