/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.massedynamic.eclipse.jmx.model;

import org.massedynamic.eclipse.jmx.data.ObjectNameKeyValue;

/**
 * TODO: Comment.
 * 
 * @author Mark Masse
 */
public final class ObjectNameKeyValueModel extends
        AbstractObjectNameKeyValueModel<ObjectNameKeyValueModel, String, ObjectNameKeyValue> {

    /**
     * TODO: Comment.
     * 
     * @param keyValuePairString
     * @param domainModel
     * @param jmxConnectionModel
     */
    ObjectNameKeyValueModel(String keyValuePairString, DomainModel domainModel, JmxConnectionModel jmxConnectionModel) {
        super(keyValuePairString, domainModel, jmxConnectionModel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.massedynamic.eclipse.zookeeper.model.jmx.AbstractObjectNameKeyValueModel#getObjectNameKeyValue()
     */
    @Override
    public ObjectNameKeyValue getObjectNameKeyValue() {
        return getData();
    }

    @Override
    protected ObjectNameKeyValueModel getThis() {
        return this;
    }

}
