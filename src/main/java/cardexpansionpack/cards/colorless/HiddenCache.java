package cardexpansionpack.cards.colorless;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class HiddenCache extends BaseCard {
    public static final String ID = makeID("HiddenCache");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.POWER,
        CardRarity.RARE,
        CardTarget.SELF,
        2
    );

    private static final int EXTRA_DRAW = 2;

    public HiddenCache() {
        super(ID, INFO);
        setMagic(EXTRA_DRAW);
        setCostUpgrade(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new HiddenCache();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DrawPower(p, magicNumber), magicNumber));
    }

}
