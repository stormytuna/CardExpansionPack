package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.ShieldOfFaithPower;
import cardexpansionpack.util.CardStats;

public class ShieldOfFaith extends BaseCard {
    public static final String ID = makeID("ShieldOfFaith");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    private static final int BLOCK_GAIN = 4;
    private static final int BLOCK_GAIN_UPGRADE = 1;

    public ShieldOfFaith() {
        super(ID, INFO);
        setMagic(BLOCK_GAIN, BLOCK_GAIN_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ShieldOfFaith();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ShieldOfFaithPower(p, magicNumber)));
    }
}

