package cardexpansionpack.cards.purple;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.actions.CondemnAction;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Condemn extends BaseCard {
    public static final String ID = makeID("Condemn");

    private static final CardStats INFO = new CardStats(
        CardColor.PURPLE,
        CardType.ATTACK,
        CardRarity.RARE,
        CardTarget.ENEMY,
        2
    );

    public Condemn() {
        super(ID, INFO);
        setEthereal(true, false);
        setDamage(5);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Condemn();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(p, damage, DamageType.NORMAL);
        addToBot(new CondemnAction(m, info));
    }
}

