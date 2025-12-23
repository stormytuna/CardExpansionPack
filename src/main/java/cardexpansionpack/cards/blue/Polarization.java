package cardexpansionpack.cards.blue;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.LockOnPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Polarization extends BaseCard {
    public static final String ID = makeID("Polarization");

    private static final CardStats INFO = new CardStats(
        CardColor.BLUE,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        1
    );

    private static final int LOCKON_AMOUNT = 2;
    private static final int LOCKON_UPGRADE = 1;

    public Polarization() {
        super(ID, INFO);
        setMagic(LOCKON_AMOUNT, LOCKON_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Polarization();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChannelAction(new Lightning()));
        addToBot(new ApplyPowerAction(m, p, new LockOnPower(m, this.magicNumber)));
    }
}

