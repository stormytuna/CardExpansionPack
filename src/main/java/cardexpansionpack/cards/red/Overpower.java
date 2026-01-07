package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.OverpowerPower;
import cardexpansionpack.util.CardStats;

public class Overpower extends BaseCard {
    public static final String ID = makeID("Overpower");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    private static final int AMOUNT = 1;

    public Overpower() {
        super(ID, INFO);
        setCostUpgrade(0);
        setMagic(AMOUNT);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Overpower();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new OverpowerPower(p, magicNumber)));
    }
}

