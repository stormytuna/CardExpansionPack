package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class Beatdown extends BaseCard {
    public static final String ID = makeID("Beatdown");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.ATTACK,
        CardRarity.COMMON,
        CardTarget.ENEMY,
        1
    );

    private static final int DAMAGE = 4;
    private static final int DAMAGE_UPGRADE = 1;
    private static final int HITS = 2;

    public Beatdown() {
        super(ID, INFO);
        setDamage(DAMAGE, DAMAGE_UPGRADE);
        setMagic(HITS);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Beatdown();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int numHits = magicNumber;
        for (AbstractCard c : p.hand.group) {
            if (c.type == CardType.STATUS) {
                numHits++;
            }
        }

        for (int i = 0; i < numHits; i++) {
            DamageInfo info = new DamageInfo(p, damage);
            addToBot(new DamageAction(m, info, AttackEffect.BLUNT_LIGHT));
        }
    }
}

