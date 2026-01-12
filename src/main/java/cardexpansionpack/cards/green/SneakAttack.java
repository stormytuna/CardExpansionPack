package cardexpansionpack.cards.green;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class SneakAttack extends BaseCard {
    public static final String ID = makeID("SneakAttack");

    private static final CardStats INFO = new CardStats(
        CardColor.GREEN,
        CardType.ATTACK,
        CardRarity.UNCOMMON,
        CardTarget.ENEMY,
        1
    );

    private static final int DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 2;
    private static final int NUM_HITS = 2;

    public SneakAttack() {
        super(ID, INFO);
        setDamage(DAMAGE, DAMAGE_UPGRADE);
        setMagic(NUM_HITS);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SneakAttack();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            DamageInfo info = new DamageInfo(p, damage);
            addToBot(new DamageAction(m, info, AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        int realBaseDamage = this.baseDamage;

        AbstractPower dex = AbstractDungeon.player.getPower(DexterityPower.POWER_ID);
        if (dex != null) {
            baseDamage += dex.amount;
        }

        super.calculateCardDamage(m);

        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }
}
