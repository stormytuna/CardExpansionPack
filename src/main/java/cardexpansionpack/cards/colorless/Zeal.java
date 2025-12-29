package cardexpansionpack.cards.colorless;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Zeal extends BaseCard {
    public static final String ID = makeID("Zeal");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    private static final int STRENGTH_AND_DEX = 1;
    private static final int STRENGTH_AND_DEX_UPGRADE = 1;


    public Zeal() {
        super(ID, INFO);
        setMagic(STRENGTH_AND_DEX, STRENGTH_AND_DEX_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Zeal();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
    }

}
