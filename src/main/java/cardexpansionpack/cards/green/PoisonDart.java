package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class PoisonDart extends BaseCard {
    public static final String ID = makeID("PoisonDart");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.SKILL,
        CardRarity.COMMON,
        CardTarget.ENEMY,
        0
    );

    private static final int POISON = 3;
    private static final int POISON_UPGRADE = 1;

    public PoisonDart() {
        super(ID, INFO);
        setMagic(POISON, POISON_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new PoisonDart();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, magicNumber)));
    }
}
