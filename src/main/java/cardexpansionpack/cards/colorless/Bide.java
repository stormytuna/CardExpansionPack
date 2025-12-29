package cardexpansionpack.cards.colorless;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Bide extends BaseCard {
    public static final String ID = makeID("Bide");

    private static final CardStats INFO = new CardStats(
        CardColor.COLORLESS,
        CardType.SKILL,
        CardRarity.UNCOMMON,
        CardTarget.SELF,
        0
    );

    private static final int VIGOR = 4;
    private static final int VIGOR_UPGRADE = 2;


    public Bide() {
        super(ID, INFO);
        setMagic(VIGOR, VIGOR_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Bide();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new VigorPower(p, magicNumber)));
    }

}
