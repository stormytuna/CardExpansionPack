package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class LashOut extends BaseCard {
    public static final String ID = makeID("LashOut");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.ATTACK,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        1
    );

    private static final int DAMAGE = 3;
    private static final int HITS = 2;
    private static final int HITS_UPGRADE = 1;
    private static final int HITS_PER_EXHAUST = 1;

    public LashOut() {
        super(ID, INFO);
        setDamage(DAMAGE);
        setMagic(HITS, HITS_UPGRADE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new LashOut();
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new ExhaustToHandAction(this));
        upgradeMagicNumber(HITS_PER_EXHAUST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(p, damage);
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new DamageAction(m, info, AttackEffect.BLUNT_LIGHT));
        }
    }
}

