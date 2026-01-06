package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.powers.HoneBladePower;
import cardexpansionpack.util.CardStats;

public class HoneBlade extends BaseCard {
    public static final String ID = makeID("HoneBlade");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.POWER,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        1
    );

    private static final int UPGRADE_AMOUNT = 1;
    private static final int UPGRADE_AMOUNT_UPGRADE = 1;

    public HoneBlade() {
        super(ID, INFO);
        setMagic(UPGRADE_AMOUNT, UPGRADE_AMOUNT_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new HoneBlade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HoneBladePower(p, magicNumber), magicNumber));
    }
}

