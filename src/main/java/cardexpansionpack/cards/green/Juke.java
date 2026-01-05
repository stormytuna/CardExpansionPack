package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Juke extends BaseCard {
    public static final String ID = makeID("Juke");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.SELF,
        0
    );

    private static final int TEMP_DEX = 2;
    private static final int TEMP_DEX_UPGRADE = 2;

    public Juke() {
        super(ID, INFO);
        setMagic(TEMP_DEX, TEMP_DEX_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Juke();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, magicNumber)));
    }
}
